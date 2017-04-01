package com.esgi.al1.blogws.models;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Chris GAGOUDE on 01/04/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostDTO {
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String text;
}
