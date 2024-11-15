package net.kegui.server.ai.function;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Description("Get the weather in location")
@Service
public class MockWeather implements Function<MockWeather.Request, MockWeather.Response> {

	public enum Unit {

		C, F

	}

	public record Request(String location, Unit unit) {
	}

	public record Response(double temp, Unit unit) {
	}

	public Response apply(Request request) {
		return new Response(30.0, Unit.C);
	}

}