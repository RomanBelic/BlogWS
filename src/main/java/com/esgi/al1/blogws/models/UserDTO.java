package com.esgi.al1.blogws.models;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Chris GAGOUDE on 01/04/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private int id;
    @NotNull
    private UserType userType;
}
