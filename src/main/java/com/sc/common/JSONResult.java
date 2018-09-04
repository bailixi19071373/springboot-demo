package com.sc.common;
public class JSONResult {
private boolean ok;
private String info;
//boostrap检验框架要的返回值
private boolean valid;

public boolean isValid() {
	return valid;
}

public void setValid(boolean valid) {
	this.valid = valid;
}

public String getInfo() {
	return info;
}

public void setInfo(String info) {
	this.info = info;
}

public boolean isOk() {
	return ok;
}

public void setOk(boolean ok) {
	this.ok = ok;
}

@Override
public String toString() {
	return "JSONResult [ok=" + ok + ", info=" + info + ", valid=" + valid + "]";
}

}
