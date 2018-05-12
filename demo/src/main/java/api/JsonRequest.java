package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
	"friends",
	"friend",
	"requestor",
	"target"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JsonRequest implements Serializable {
	private static final long serialVersionUID = 1898971995174514558L;
	@JsonProperty("friends")
	private List<String> friends = new ArrayList<String>();
	@JsonProperty("friend")
	private String friend;
	@JsonProperty("requestor")
	private String requestor;
	@JsonProperty("target")
	private String target;
	@JsonProperty("sender")
	private String sender;
	@JsonProperty("text")
	private String text;
	
	
	public JsonRequest(List<String> friends) {
		super();
		this.friends = friends;
	}

}
