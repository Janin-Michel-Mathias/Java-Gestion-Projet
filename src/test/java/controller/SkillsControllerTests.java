package controller;
import GlobalVariables.EnvironmentVariable;
import modeles.Skill;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class SkillsControllerTests {

    public static class SkillsControllerTest {
        @Test
        public void testGetAllSkills() throws SQLException {
            // Arrange
            Connection mockConnection = mock(Connection.class);
            Statement mockStatement = mock(Statement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(mockConnection.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true, false); // Simulate one row in the result set

            EnvironmentVariable.setConnection(mockConnection);

            // Act
            List<Skill> skills = SkillsController.getAllSkills();

            // Assert
            assertEquals(1, skills.size());
            verify(mockConnection, times(1)).createStatement();
            verify(mockStatement, times(1)).executeQuery(anyString());
            verify(mockResultSet, times(2)).next();
        }
    }

    @Test
    public void testGetSkillById() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one row in the result set
        when(mockResultSet.getInt("id")).thenReturn(1);
        EnvironmentVariable.setConnection(mockConnection);

        // Act
        Skill skill = SkillsController.getSkillById(1);

        // Assert
        assertEquals(1, skill.getId());
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    public void testCreateSkill() throws SQLException {
        Skill skill = new Skill("Java");
        String skill_name = skill.getSkillName();
        // Arrange
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate one row affected

        // Mock the setString method
        doNothing().when(mockPreparedStatement).setString(1, skill_name);

        EnvironmentVariable.setConnection(mockConnection);

        // Act
        Skill createdSkill = SkillsController.createSkill(skill);

        // Assert
        assertEquals(skill, createdSkill);
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setString(anyInt(), anyString()); // Verify that setString was called
    }


    @Test
    public void testUpdateSkill() throws SQLException {
        Skill skill = new Skill("Java");
        String skill_name = skill.getSkillName();
        // Arrange
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate one row affected

        // Mock the setString method
        doNothing().when(mockPreparedStatement).setString(1, skill_name);
        doNothing().when(mockPreparedStatement).setInt(2, skill.getId());

        EnvironmentVariable.setConnection(mockConnection);

        // Act
        Skill updatedSkill = SkillsController.updateSkill(skill);

        // Assert
        assertEquals(skill, updatedSkill);
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setString(anyInt(), anyString()); // Verify that setString was called
        verify(mockPreparedStatement, times(1)).setInt(anyInt(), anyInt()); // Verify that setInt was called
    }

    @Test
    public void testDeleteSkill() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate one row affected

        EnvironmentVariable.setConnection(mockConnection);

        // Act
        SkillsController.deleteSkill(1);

        // Assert
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setInt(anyInt(), anyInt()); // Verify that setInt was called
    }
}
