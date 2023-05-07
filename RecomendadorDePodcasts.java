import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.ShowSimplified;
import com.wrapper.spotify.requests.data.search.simplified.SearchShowsRequest;

import java.io.IOException;

public class RecomendadorDePodcasts {

  private static final String CLIENT_ID = "TU_CLIENT_ID";
  private static final String CLIENT_SECRET = "TU_CLIENT_SECRET";
  private static final String REDIRECT_URI = "TU_REDIRECT_URI";
  private static final String QUERY = "podcast";

  public void recomendarPodcast() {
    try {
      // Conectamos con la API de Spotify
      SpotifyApi spotifyApi = new SpotifyApi.Builder()
              .setClientId(CLIENT_ID)
              .setClientSecret(CLIENT_SECRET)
              .setRedirectUri(REDIRECT_URI)
              .build();

      // Obtenemos un token de acceso a la API de Spotify
      String accessToken = spotifyApi.clientCredentials().build().execute().getAccessToken();
      spotifyApi.setAccessToken(accessToken);

      // Realizamos la búsqueda de podcasts en Spotify
      SearchShowsRequest searchShowsRequest = spotifyApi.searchShows(QUERY)
              .market("ES")
              .limit(1)
              .offset(0)
              .build();

      Paging<ShowSimplified> shows = searchShowsRequest.execute();

      // Mostramos el resultado de la búsqueda al usuario
      if (shows.getTotal() > 0) {
        ShowSimplified show = shows.getItems()[0];
        System.out.println("Este es el podcast recomendado para ti: " + show.getName());
        System.out.println("Escúchalo en Spotify: " + show.getExternalUrls().get("spotify"));
      } else {
        System.out.println("No se encontró ningún podcast interesante para ti");
      }

    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Ocurrió un error al conectar con la API de Spotify: " + e.getMessage());
    }
  }
}