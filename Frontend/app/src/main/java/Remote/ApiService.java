package Remote;

import java.util.ArrayList;

import objects.Exercise;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    String BASE_URL = "/weightworks/";

    @GET(BASE_URL + "exercises")
    Call<ArrayList<Exercise>> getExercises();

    @POST(BASE_URL + "exercises")
    Call<Exercise> addExercise(@Body Exercise exercise);
}
