package com.rtarcisio.gatewayms.config;

public class KeycloakRoleConverter{//  implements Converter<Jwt, Collection<GrantedAuthority>> {

//    @Override
//    public Collection<GrantedAuthority> convert(Jwt source) {
//        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");
//        if (realmAccess == null || realmAccess.isEmpty()) {
//            return new ArrayList<>();
//        }
//        Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles"))
//                .stream().map(roleName -> "ROLE_" + roleName)
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        return returnValue;
//    }

}
