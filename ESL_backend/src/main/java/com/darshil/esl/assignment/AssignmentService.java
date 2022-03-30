package com.darshil.esl.assignment;

import com.darshil.esl.exception.AssignmentNotFoundException;
import com.darshil.esl.exception.EmptyFieldException;
import com.darshil.esl.exception.InvalidRequestException;
import com.darshil.esl.exception.RowNotChangedException;
import com.darshil.esl.players.Player;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignmentService {

    private AssignmentDao assignmentDao;

    public AssignmentService (AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public List<Assignments> selectAllAssignments() {

        List<Assignments> assignments = assignmentDao.selectAllAssignments();
        if (assignments.isEmpty()) {
            throw new InvalidRequestException("No assignments exist!");
        }
        return assignments;
    }

    

    public List<Player> selectAllPlayersForUser(Integer user_id) {
        checkIfIdValid(user_id);

        List<Player> assignments = assignmentDao.selectAllPlayersForUser(user_id);

        if(assignments == null) {
            throw new InvalidRequestException("No players found, please try again.");
        }
            return assignments;
    }

    public void insertAssignment(Assignments assignments) {
        List<Player> playerList = selectAllPlayersForUser(assignments.getUser_id());
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getId() == assignments.getPlayer_id()) {
                throw new InvalidRequestException("You already have the player!");
            }
        }

        if(assignments.getUser_id() == null) {
            throw new InvalidRequestException("User id cannot be null");
        }
        if(assignments.getPlayer_id() == null) {
            throw new InvalidRequestException("Player id cannot be null");
        }

        Integer rowsAffected = assignmentDao.insertAssignment(assignments);
        if (rowsAffected != 1) {
            throw new RowNotChangedException("Assignment was not added");
        }
    }

    public void deleteAssignment(Integer assignment_id) {
        checkIfIdValid(assignment_id);

        Assignments assignments = assignmentDao.selectAssignmentById(assignment_id);
        if (assignments == null) {
            throw new AssignmentNotFoundException("Assignment with ID " + assignment_id + " does not exist.");
        }

        Integer rowsAffected = assignmentDao.deleteAssignment(assignment_id);
        if (rowsAffected != 1) {
            throw new RowNotChangedException("Assignment with ID " + assignment_id + " was not deleted");
        }
    }

    public void checkIfIdValid(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidRequestException("Please enter a valid ID");
        }
    }
}
