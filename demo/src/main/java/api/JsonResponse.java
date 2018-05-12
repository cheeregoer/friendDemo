package api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonResponse {
	
	private Boolean success;
	private List<String> friends = new ArrayList<>();
	private List<String> recipients = new ArrayList<>();
	private Integer count;
	private String statusMessage;

}
