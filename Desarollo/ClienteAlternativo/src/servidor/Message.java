package servidor;

import java.io.Serializable;

import com.google.gson.Gson;

public class Message implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5284480614138809947L;
	
	private String type;
	private Object data;

	public Message(String type, Object data) {
		super();
		this.type = type;
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
