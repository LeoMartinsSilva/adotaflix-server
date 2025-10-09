package br.com.adotaflix.server.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {

}
