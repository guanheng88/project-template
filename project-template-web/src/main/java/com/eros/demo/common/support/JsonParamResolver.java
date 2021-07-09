package com.eros.demo.common.support;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * json映射到controller多参数解析器
 * 
 * @author guanheng
 *
 */
public class JsonParamResolver implements HandlerMethodArgumentResolver {

    private final String JSON_PARAM = "JSON_PARAM";

    @Override
    public boolean supportsParameter(MethodParameter param) {
        return param.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws IOException, MissingServletRequestParameterException {

        JSONObject json = getJsonObject(webRequest);
        return parseObject(param, json);
    }

    /**
     * Build JSONObject with request.
     * 
     * @param webRequest
     * @return
     * @throws IOException
     * @throws Exception
     */
    private JSONObject getJsonObject(NativeWebRequest webRequest) throws IOException {
        String json = getJsonString(webRequest);
        return JSONObject.parseObject(json);
    }

    /**
     * Get json character string from request.
     * 
     * @param webRequest
     * @return
     * @throws IOException
     * @throws Exception
     */
    private String getJsonString(NativeWebRequest webRequest) throws IOException {
        String json = (String) webRequest.getAttribute(JSON_PARAM, RequestAttributes.SCOPE_REQUEST);
        if (StringUtils.isEmpty(json)) {
            HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
            HttpInputMessage inputMessage = new ServletServerHttpRequest(servletRequest);
            json = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
            webRequest.setAttribute(JSON_PARAM, json, RequestAttributes.SCOPE_REQUEST);
        }

        return json;
    }

    /**
     * Convert from json character string to parameter object.
     * 
     * @param param
     * @param json
     * @return
     * @throws MissingServletRequestParameterException
     * @throws Exception
     */
    private Object parseObject(MethodParameter param, JSONObject json) throws MissingServletRequestParameterException {
        JsonParam annotation = param.getParameterAnnotation(JsonParam.class);
        String paramName = StringUtils.isEmpty(annotation.value()) ? param.getParameterName() : annotation.value();
        String defaultValue = annotation.defaultValue();
        String jsonValue = json == null ? null : json.getString(paramName);
        if (ValueConstants.DEFAULT_NONE.equals(defaultValue) && StringUtils.isEmpty(jsonValue)) {
            if (annotation.required()) {
                throw new MissingServletRequestParameterException(paramName, param.getGenericParameterType().toString());
            }
            else {
                return null;
            }
        }
        else if (StringUtils.isEmpty(jsonValue)) {
            jsonValue = defaultValue;
        }

        if (String.class.isAssignableFrom(param.getParameterType())) {
            return jsonValue;
        }
        else {
            return JSON.parseObject(jsonValue, param.getGenericParameterType());
        }
    }
}
