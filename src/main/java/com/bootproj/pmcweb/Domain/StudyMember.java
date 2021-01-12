package com.bootproj.pmcweb.Domain;

import lombok.*;

@Builder
@AllArgsConstructor
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
