package com.erban.api.http;

import com.erban.api.exception.XiaoMeiCredentialsException;
import com.erban.api.exception.XiaoMeiIOException;
import com.erban.api.exception.XiaoMeiJSONException;
import com.erban.api.exception.XiaoMeiOtherException;
import com.erban.api.builder.JSONBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by huzhi on 15-2-17.
 */
public interface HttpApi {

    abstract public <T> void doHttpRequestList(HttpRequestBase httpRequest,
                                               JSONBuilder<T> builder, Collection<T> collection) throws XiaoMeiCredentialsException,
            XiaoMeiIOException, XiaoMeiJSONException, XiaoMeiOtherException;

    abstract public <T> T doHttpRequestObject(HttpRequestBase httpRequest,
                                              JSONBuilder<T> builder) throws XiaoMeiCredentialsException,
            XiaoMeiIOException, XiaoMeiJSONException, XiaoMeiOtherException;

    abstract public String doHttpRequestString(HttpRequestBase httpRequest)
            throws XiaoMeiCredentialsException, XiaoMeiIOException, XiaoMeiOtherException;

    abstract public InputStream doHttpRequestInputStream(
            HttpRequestBase httpRequest) throws XiaoMeiCredentialsException,
            XiaoMeiIOException, XiaoMeiOtherException;


    abstract public HttpGet createHttpGet(String url,NameValuePair... nameValuePairs);

    abstract public HttpPost createHttpPost(String url,NameValuePair... nameValuePairs) throws XiaoMeiIOException;

    abstract public HttpResponse doHttpRequestResponse(
            HttpRequestBase httpRequest) throws XiaoMeiCredentialsException,
            XiaoMeiIOException, XiaoMeiOtherException;

}