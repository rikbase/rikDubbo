package com.rik.dubbo.constant;

import org.springframework.context.annotation.Description;

@Description("����������")
public enum Constants {
	STATUS("status"),MSG("msg"),RST("result"),DB_MONGO("mongo"),DB_REDIS("redis");
	private final String v;
    private Constants(String v) {this.v = v;}
    public String get() {return v;}
}
