package doan.quanlykho.be.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Data
@Getter
@Setter
public class JwtAuthenticationResponse {
	Integer accountId;
	String accessToken;
	String refreshToken;
	String type;
	private long expiryDuration = 7 * 24 * 60 * 60 * 1000;

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