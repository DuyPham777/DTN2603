package backend.repository;

import entity.Position;
import entity.PositionName;

import java.util.List;

public interface IQLPositionRepository {
    List<Position> getPositions();
    boolean suaPositionNameTheoId(Integer positionId, PositionName positionName);
    boolean xoaPositionTheoId(Integer positionId);
    boolean themPosition(PositionName positionName);
    boolean isPositionExist(int id);
}
