package com.istudy.dada.chapter7;

public class ApiContext {

    private Api api;

    public ApiContext() {
        this.api = new Api();
    }

    public void request() throws RequestFailureException {

        try {
            api.request();
        } catch (InvalidParamException e) {
            throw new RequestFailureException(e);
        } catch (StoreException e) {
            throw new RequestFailureException(e);
        } catch (NetworkException e) {
            throw new RequestFailureException(e);
        }
    }
}
