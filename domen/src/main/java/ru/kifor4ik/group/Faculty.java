package ru.kifor4ik.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Faculty {
    private Long id;
    private String shortName;
    private String fullName;
}
