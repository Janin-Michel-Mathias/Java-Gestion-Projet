package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.SkillStacks;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StackControllerTests {
    @Test
    public void testGetAllStacks() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        // Mock the ResultSet getters
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Stack");
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getString("skill_name")).thenReturn("java");
        EnvironmentVariable.setConnection(mockConnection);
        // Act
        List<SkillStacks> stacks = StackController.getAllStacks();

        // Assert
        assertFalse(stacks.isEmpty());

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(3)).next();
    }

    @Test
    public void testGetStackByNameProject() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name_project")).thenReturn("Test Project");
        when(mockResultSet.getInt("id_skill")).thenReturn(1);
        when(mockResultSet.getInt("numberDev")).thenReturn(1);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        // Mock the ResultSet getters
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name_project")).thenReturn("Test Project");
        when(mockResultSet.getInt("id_skill")).thenReturn(1);
        when(mockResultSet.getInt("numberDev")).thenReturn(1);
        EnvironmentVariable.setConnection(mockConnection);
        // Act
        List<SkillStacks> stacks = StackController.getStackByNameProject("Test Project");

        // Assert
        assertFalse(stacks.isEmpty());

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "Test Project");
        verify(mockPreparedStatement, times(2)).executeQuery();
        verify(mockResultSet, times(3)).next();
    }

    @Test
    public void createStack() throws SQLException {
        SkillStacks stack = new SkillStacks();
        stack.setId(1);
        stack.setProjectName("Test Project");
        stack.setSkillName("java");
        stack.setNumberOfDevelopers(1);

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        SkillStacks result = StackController.createStack(stack);

        assertEquals(1, result.getId());
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void updateStack() throws SQLException {
        SkillStacks stack = new SkillStacks();
        stack.setId(1);
        stack.setProjectName("Test Project");
        stack.setSkillName("java");
        stack.setNumberOfDevelopers(1);

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        SkillStacks result = StackController.updateStack(stack);

        assertEquals(1, result.getId());
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
public void deleteStack() throws SQLException {
        SkillStacks stack = new SkillStacks();
        stack.setId(1);
        stack.setProjectName("Test Project");
        stack.setSkillName("java");
        stack.setNumberOfDevelopers(1);

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        EnvironmentVariable.setConnection(mockConnection);

        StackController.deleteStack(stack.getProjectName());

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void deleteSkillStack() throws SQLException {
        SkillStacks stack = new SkillStacks();
        stack.setId(1);
        stack.setProjectName("Test Project");
        stack.setSkillName("java");
        stack.setNumberOfDevelopers(1);

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        StackController.deleteSkillStack(stack.getId(), stack.getProjectName());

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
