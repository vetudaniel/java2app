package service.interfaces;

import domain.QuestionEntity;

public interface FetchQuestionCallback {
    void onQuestionFetched(QuestionEntity questionEntity);
    void onError(Throwable t);
}
