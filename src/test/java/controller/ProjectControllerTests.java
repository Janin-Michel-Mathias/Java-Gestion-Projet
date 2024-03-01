package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Project;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ProjectControllerTests {
    @Test
    public void testGetAllProjects() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time

        // Mock the ResultSet getters
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Project");
        when(mockResultSet.getInt("id_priority")).thenReturn(2);
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getDate("date_start")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(mockResultSet.getDate("date_end")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        EnvironmentVariable.setConnection(mockConnection);
        // Act
        List<Project> projects = ProjectController.getAllProjects();

        // Assert
        assertFalse(projects.isEmpty());
        Project project = projects.get(0);
        assertEquals(1, project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals(2, project.getPriorityId());
        assertEquals("Test Description", project.getDescription());
    }

    @Test
    public void testGetProjectById() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time

        // Mock the ResultSet getters
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Project");
        when(mockResultSet.getInt("id_priority")).thenReturn(2);
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getDate("date_start")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(mockResultSet.getDate("date_end")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        EnvironmentVariable.setConnection(mockConnection);
        // Act
        Project project = ProjectController.getProjectById(1);

        // Assert
        assertEquals(1, project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals(2, project.getPriorityId());
        assertEquals("Test Description", project.getDescription());
    }

    @Test
    public void testCreateProject() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Project project = new Project();
        project.setName("Test Project");
        project.setPriorityId(1);
        project.setDescription("Test Description");
        project.setStartDate(new java.sql.Date(System.currentTimeMillis()));
        project.setEndDate(new java.sql.Date(System.currentTimeMillis()));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate one row affected

        // Mock the setString method
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(mockPreparedStatement).setDate(anyInt(), any(java.sql.Date.class));
        EnvironmentVariable.setConnection(mockConnection);

        // Act
        Project newProject = ProjectController.createProject(project);

        // Assert
        assertEquals(1, newProject.getId());
        assertEquals(project.getName(), newProject.getName());
        assertEquals(project.getPriorityId(), newProject.getPriorityId());
        assertEquals(project.getDescription(), newProject.getDescription());
    }

    @Test
    public void testUpdateProject() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        Project project = new Project();
        project.setId(1);
        project.setName("Test Project");
        project.setPriorityId(1);
        project.setDescription("Test Description");
        project.setStartDate(new java.sql.Date(System.currentTimeMillis()));
        project.setEndDate(new java.sql.Date(System.currentTimeMillis()));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(mockPreparedStatement).setDate(anyInt(), any(java.sql.Date.class));
        EnvironmentVariable.setConnection(mockConnection);

        // Act
        Project updatedProject = ProjectController.updateProject(project);

        // Assert
        assertEquals(project.getId(), updatedProject.getId());
        assertEquals(project.getName(), updatedProject.getName());
        assertEquals(project.getPriorityId(), updatedProject.getPriorityId());
        assertEquals(project.getDescription(), updatedProject.getDescription());
    }

    @Test
    public void testGetProjectByName() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time

        // Mock the ResultSet getters
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Project");
        when(mockResultSet.getInt("id_priority")).thenReturn(2);
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getDate("date_start")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(mockResultSet.getDate("date_end")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        EnvironmentVariable.setConnection(mockConnection);
        // Act
        Project project = ProjectController.getProjectByName("Test Project");

        // Assert
        assertEquals(1, project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals(2, project.getPriorityId());
        assertEquals("Test Description", project.getDescription());
    }

    @Test
    public void testDeleteProject() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        Project project = new Project();
        project.setId(1);
        project.setName("Test Project");
        project.setPriorityId(1);
        project.setDescription("Test Description");
        project.setStartDate(new java.sql.Date(System.currentTimeMillis()));
        project.setEndDate(new java.sql.Date(System.currentTimeMillis()));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        // Act
        ProjectController.deleteProject(project.getId());

        // Assert
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setInt(anyInt(), anyInt());
    }
}
