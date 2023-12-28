package service.interfaces;

import domain.GameEntity;

public interface FetchGameCallback {
    void onGameFetched(GameEntity gameEntity);
    void onError(Throwable t);
}
