package delegateTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.internetcomputing.workshop.delegate.TsscGameDelegate;
import co.edu.icesi.internetcomputing.workshop.delegate.TsscGameDelegateImp;
import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

@RunWith(MockitoJUnitRunner.class)
public class TestGameDelegate {
	
	@Mock
	private RestTemplate restTemplate;
	
	private TsscGameDelegate gameDelegate;
	
	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		TsscGameDelegateImp gamdel = new TsscGameDelegateImp();
		gamdel.setRestTemplate(restTemplate);
		gameDelegate = gamdel;
	}
	
	@Test
	public void addGameTest() {
		TsscGame game = new TsscGame();
		game.setName("Goku");
		TransactionBody<TsscGame> body = new TransactionBody<TsscGame>();
		body.setBody(game);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<TransactionBody<TsscGame>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		game = gameDelegate.addGame(game);
		assertEquals(game.getName(), "Goku");
	}
	
	@Test
	public void getGameTest() {
		TsscGame game = new TsscGame();
		game.setName("Vegeta");
		TransactionBody<TsscGame> body = new TransactionBody<TsscGame>();
		body.setBody(game);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<TransactionBody<TsscGame>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		game = gameDelegate.getGame(game.getId());
		assertEquals(game.getName(), "Vegeta");
	}
	
	@Test
	public void getAllGameTest() {
		TransactionBody<List<TsscGame>> body = new TransactionBody<List<TsscGame>>();
		List<TsscGame> list = new ArrayList<TsscGame>();
		TsscGame game1 = new TsscGame();
		game1.setName("Goku");
		list.add(game1);
		TsscGame game2 = new TsscGame();
		game2.setName("Vegeta");
		list.add(game2);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<TsscGame>>> response = new ResponseEntity<TransactionBody<List<TsscGame>>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<TsscGame> iterableGame = gameDelegate.getAllGames();
		int size = 0;
		for (TsscGame ad : iterableGame) {
			size++;
		}
		assertEquals(size, 2);
	}
	
	@Test
	public void updateGameTest() {
		TsscGame game = new TsscGame();
		game.setName("Gojan");
		TransactionBody<TsscGame> body = new TransactionBody<TsscGame>();
		body.setBody(game);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<TransactionBody<TsscGame>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		game.setName("Gohan");
		gameDelegate.updateGame(game);
		TsscGame gameN = gameDelegate.getGame(game.getId());
		assertEquals("Gohan", gameN.getName());
	}
	
}
