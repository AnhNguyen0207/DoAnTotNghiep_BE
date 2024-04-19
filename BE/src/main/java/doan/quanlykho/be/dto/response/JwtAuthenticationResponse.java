package doan.quanlykho.be.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JwtAuthenticationResponse {
	Integer accountId;
	String accessToken;
	String refreshToken;
	String type;
	private long expiryDuration;

	public JwtAuthenticationResponse(String accessToken, String refreshToken, long expiryDuration) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.type = "Bearer ";
		this.expiryDuration = expiryDuration;
	}
	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
		this.type = "Bearer ";
	}
}