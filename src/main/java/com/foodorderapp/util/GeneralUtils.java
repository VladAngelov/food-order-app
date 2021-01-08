package com.foodorderapp.util;

import com.foodorderapp.models.binding.auth.SocialProvider;
import com.foodorderapp.models.binding.user.LocalUserBindingModel;
import com.foodorderapp.models.entity.Role;
import com.foodorderapp.models.entity.User;
import com.foodorderapp.models.view.UserViewModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralUtils {

    public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public static SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.LOCAL;
    }

    public static UserViewModel buildUserInfo(LocalUserBindingModel localUser) {

        List<String> roles = localUser
                .getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        User user = localUser.getUser();

        return new UserViewModel(
                user.getId().toString(),
                user.getDisplayName(),
                user.getEmail(),
                roles);
    }
}
