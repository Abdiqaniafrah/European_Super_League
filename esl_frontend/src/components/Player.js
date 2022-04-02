const Player = ({ player,handlePlayerSelect }) => {

    return (
        <>
            <tr>
                <td>{player.player_name}</td>
                <td>{player.player_position}</td>
                <td>{player.player_club}</td>
                <td>{player.points}</td>
                <td><button onClick={() => handlePlayerSelect(player.player_club)}>Select</button></td>
            </tr>
        </>
    )
}

export default Player;