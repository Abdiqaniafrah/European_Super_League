import React from "react";
import User from "./User";
import './Leaderboard.css';


const Leaderboard = ({ userList }) => {

    const userMap = userList.map(user => {
        return (
            <User user={user} key={user.id} />
        )
    })

    return (
        <div className="leaderboard-page">
            <table className='leaderboard-table'>
                <tr className="leaderboard-headings">
                    <th>Rank</th>
                    <th>Team Name</th>
                    <th>Total Points</th>
                </tr>
                {userMap}
            </table>
        </div>
    )
}
export default Leaderboard;