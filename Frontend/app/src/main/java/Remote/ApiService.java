package Remote;

import objects.Exercise;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/getExercise")
    Call<Exercise> getExercise(@Body Exercise exercise);

    @POST("/addExercise")
    Call<Exercise> addExercise(@Body Exercise exercise);
}
