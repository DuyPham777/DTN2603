package backend.controller;

import backend.service.IQLPositionService;
import backend.service.impl.QLPositionServiceImpl;
import entity.Position;
import entity.PositionName;

import java.util.List;

public class QLPositionController {
    private IQLPositionService service;

    public QLPositionController() {
        service = new QLPositionServiceImpl();
    }

    public List<Position> getPositions() {
        List<Position> positions = service.getPositions();
        return positions;
    }

    public boolean xoaPositionTheoId(Integer positionId) {
        return service.xoaPositionTheoId(positionId);
    }

    public boolean suaPositionNameTheoId(Integer positionId, PositionName positionName) {
        return service.suaPositionNameTheoId(positionId, positionName);
    }

    public boolean themPosition(PositionName positionName) {

        return service.themPosition(positionName);
    }
    public boolean isPositionExist(int id){
        return service.isPositionExist(id);
    }
}
