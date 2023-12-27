package service.interfaces;

import domain.PlayerEntity;
import domain.QuestionEntity;

public interface FetchPlayerCallback {
    void onQuestionFetched(QuestionEntity questionEntity);
    void onError(Throwable t);
}
