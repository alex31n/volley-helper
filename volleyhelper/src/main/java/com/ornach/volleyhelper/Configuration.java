package com.ornach.volleyhelper;

public class Configuration {

    private int timeout= 1000*9;
    private boolean isCache = true;
    private int maxNumRetries=0;

    private Configuration() {
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    protected int getMaxNumRetries() {
        return maxNumRetries;
    }
    /*
    public void setMaxNumRetries(int maxNumRetries) {
        this.maxNumRetries = maxNumRetries;
    }*/


    public static class Builder{
        Configuration config;
        public Builder() {
            config = new Configuration();
        }

        public Builder setTimeout(int timeout) {
            config.timeout = timeout;
            return this;
        }

        public Builder setCache(boolean cache) {
            config.isCache = cache;
            return this;
        }
        /*public Builder setMaxNumRetries(int maxNumRetries) {
            config.maxNumRetries = maxNumRetries;
            return this;
        }*/

        public Configuration build(){
            return config;
        }
    }
}
