package org.generation.italy.legion.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.legion.model.entities.*;

import java.util.Optional;
import java.util.stream.StreamSupport;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTeacherDto {
   private long id;
   private String firstname;
   private String lastname;
   private Sex sex;
   private Level level;
   private String pIVA;
   private String skillName;
   private long skillId;
   private Level skillLevel;


//   public SimpleTeacherDto(long id, String firstname, String lastname, Sex sex, Level level, String pIVA,
//                              String skillName, long skillId, Level skillLevel) {
//      this.id = id;
//      this.firstname = firstname;
//      this.lastname = lastname;
//      this.sex = sex;
//      this.level = level;
//      this.pIVA = pIVA;
//      this.skillName = skillName;
//      this.skillId = skillId;
//      this.skillLevel = skillLevel;
//
//   }
   public static SimpleTeacherDto fromEntity(Teacher t, long skillId){
      Optional<Competence> oC = t.getCompetenceForSkill(skillId);
      Competence c = oC.get();
      String skillName = c.getSkill().getName();
      Level level = c.getLevel();
      return new SimpleTeacherDto(t.getId(), t.getFirstname(), t.getLastname(), t.getSex(), t.getLevel(), t.getPIVA(),
                                    skillName, skillId, level);
   }
   public static Iterable<SimpleTeacherDto> fromEntityIterable(Iterable<Teacher> iT, long skillId){
      return StreamSupport.stream(iT.spliterator(), false)
            .map(t -> SimpleTeacherDto.fromEntity(t, skillId))
            .toList();
   }
}
