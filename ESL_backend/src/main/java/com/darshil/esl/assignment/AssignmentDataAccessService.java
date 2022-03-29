package com.darshil.esl.assignment;

import com.darshil.esl.players.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssignmentDataAccessService implements AssignmentDao {
    private JdbcTemplate jdbcTemplate;

    public AssignmentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Player> selectAllPlayersForUser(Integer user_id) {
        String sql = """
                SELECT player_name,player_position,player_club,price,goals,assists,red_cards,yellow_cards,clean_sheets,points FROM ((users INNER JOIN player_assignment ON player_assignment.user_id = users.id)
                INNER JOIN players ON player_assignment.player_id = players.id) WHERE users.id = ?;
                """;
        return jdbcTemplate.query(sql, new AssignmentMapper(), user_id);
    }

    @Override
    public int insertAssignment(Assignments assignments) {
        String sql = """
                
                """;
        return 0;
    }

    @Override
    public int deleteAssignment(Integer assignment_id) {
        return 0;
    }
}
