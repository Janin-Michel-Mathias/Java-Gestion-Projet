package modeles;

import modeles.SkillExperience;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkillExperienceTest {
    String skillName = "Node";
    int experience = 100;
    String rank = "Beginner";
    int rankNb = 1;

    SkillExperience skillExperience;

    @BeforeEach
    public void createSkillExperience() {
        skillExperience = new SkillExperience(skillName, experience, rank, rankNb);
    }

    @Test
    public void shouldGetSkillName() {
        assert(skillExperience.getSkill().equals(skillName));
    }

    @Test
    public void shouldSetSkillName() {
        String newName = "Express";
        skillExperience.setSkill(newName);
        assert(skillExperience.getSkill().equals(newName));
    }

    /*@Test
    public void shouldNotSetSkillNameEmpty() {
        skillExperience.setSkill("");
        assert(skillExperience.getSkill().equals(skillName));
    }*/

    @Test
    public void shouldGetExperience() {
        assert(skillExperience.getExperience() == experience);
    }

    @Test
    public void shouldSetExperience() {
        int newExperience = 200;
        skillExperience.setExperience(newExperience);
        assert(skillExperience.getExperience() == newExperience);
    }

    /*@Test
    public void shouldNotSetExperienceNegative() {
        int newExperience = -1;
        skillExperience.setExperience(newExperience);
        assert(skillExperience.getExperience() == experience);
    }*/

    @Test
    public void shouldGetRank() {
        assert(skillExperience.getRank().equals(rank));
    }

    @Test
    public void shouldSetRank() {
        String newRank = "Intermediate";
        skillExperience.setRank(newRank);
        assert(skillExperience.getRank().equals(newRank));
    }

    @Test
    public void shouldGetRankNb() {
        assert(skillExperience.getRankNb() == rankNb);
    }

    @Test
    public void shouldSetRankNb() {
        int newRankNb = 2;
        skillExperience.setRankNb(newRankNb);
        assert(skillExperience.getRankNb() == newRankNb);
    }

    /*@Test
    public void shouldNotSetRankNbNegative() {
        int newRankNb = -1;
        skillExperience.setRankNb(newRankNb);
        assert(skillExperience.getRankNb() == rankNb);
    }*/

}
