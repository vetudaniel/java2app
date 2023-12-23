package domain.typeconverters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AnswersListConverter {

    @TypeConverter
    public String fromAnswersList(List<String> answers) {
        if (answers == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.toJson(answers, type);
    }

    @TypeConverter
    public List<String> toAnswersList(String answersString) {
        if (answersString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(answersString, type);
    }
}