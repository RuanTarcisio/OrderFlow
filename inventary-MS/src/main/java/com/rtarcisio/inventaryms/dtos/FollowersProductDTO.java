package com.rtarcisio.inventaryms.dtos;

import java.util.Set;

public record FollowersProductDTO (Set<String> userEmail, Long ProductId, String description){
}
