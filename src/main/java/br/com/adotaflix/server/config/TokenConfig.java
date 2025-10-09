package br.com.adotaflix.server.config;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.adotaflix.server.model.Usuario;

@Component
public class TokenConfig {

	private String secret = "secret";
	
	public String generateToken(Usuario usuario) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create()
				.withClaim("userId", usuario.getId())
				.withSubject(usuario.getEmail())
				.withExpiresAt(Instant.now().plusSeconds(10000000))
				.withIssuedAt(Instant.now())
				.sign(algorithm);
	}

	public Optional<JWTUserData> validadeToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			DecodedJWT decoded = JWT.require(algorithm)
					.build()
					.verify(token);
			return Optional.of(
						JWTUserData.builder()
						.email(decoded.getSubject())
						.userId(decoded.getClaim("userId").asLong())
						.build()
					);
			
		}catch(Exception e){
			return Optional.empty();
		}
	
	}
	
}
