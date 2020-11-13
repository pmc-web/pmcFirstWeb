package com.bootproj.pmcweb.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudyMember {
   private Long id;
   private Long userId;
   private Long studyId;
   private String studyRole;
}
