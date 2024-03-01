package controller;

import org.junit.jupiter.api.Test;

import modeles.Priority;
import GlobalVariables.EnvironmentVariable;

import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PriorityControllerTests {
    @Test
    public void testGetAllPriorities() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("type")).thenReturn("High");

        EnvironmentVariable.setConnection(mockConnection);

        List<Priority> priorities = PriorityController.getAllPriorities();

        assertEquals(1, priorities.size());
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetPriorityById() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("type")).thenReturn("High");
        doNothing().when(mockStatement).setInt(anyInt(), anyInt());

        EnvironmentVariable.setConnection(mockConnection);

        Priority priority = PriorityController.getPriorityById(1);

        assertEquals(1, priority.getId());
        assertEquals("High", priority.getType());
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    public void testGetPriorityByType() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("type")).thenReturn("High");
        doNothing().when(mockStatement).setString(anyInt(), anyString());

        EnvironmentVariable.setConnection(mockConnection);

        Priority priority = PriorityController.getPriorityByType("High");

        assertEquals(1, priority.getId());
        assertEquals("High", priority.getType());
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    public void testCreatePriority() throws SQLException {
        Priority priority = new Priority();
        priority.setId(1);
        priority.setType("High");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockStatement).setString(anyInt(), anyString());
        EnvironmentVariable.setConnection(mockConnection);

        PriorityController.createPriority(priority);

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdatePriority() throws SQLException {
        Priority priority = new Priority();
        priority.setId(1);
        priority.setType("High");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockStatement).setString(anyInt(), anyString());
        doNothing().when(mockStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        PriorityController.updatePriority(priority);

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeletePriority() throws SQLException {
        Priority priority = new Priority();
        priority.setId(1);
        priority.setType("High");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        PriorityController.deletePriority(priority.getId());

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).executeUpdate();
    }
}
