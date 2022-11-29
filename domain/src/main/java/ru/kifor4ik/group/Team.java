package ru.kifor4ik.group;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Team {

    private Long id;
    private Long courseId;
    private String shortName;
    private String fullName;
}
