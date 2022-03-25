package com.tranquyet.controller.client;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.domain.CurrentWeatherInfor;
import com.tranquyet.domain.ForecastWeatherInfor;
import com.tranquyet.dto.SearchCondition;
import com.tranquyet.service.StatusWeatherService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController("weatherApi")
@RequestMapping("/api/weather")
@Slf4j
public class WeatherApi {
	@Autowired
	private StatusWeatherService weatherService;

	@PostMapping("/current")
	public ResponseEntity<CurrentWeatherInfor> getCurrentWeather(@RequestBody @NotNull SearchCondition conditio) {
		String url = "";
		log.info(conditio.toString());
		try {
			Optional<CurrentWeatherInfor> currInfo = Optional.of(weatherService.getCurrentInfor(url, conditio));
			return new ResponseEntity<>(currInfo.get(),
					currInfo.isPresent() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping("/forecast")
	public ResponseEntity<List<ForecastWeatherInfor>> getOnecallWeather(@RequestBody @NotNull SearchCondition conditio)
			throws Exception {
		String url = "";
		try {

			Optional<List<ForecastWeatherInfor>> dailyInfo = Optional
					.of(weatherService.getForecastInfor(url, conditio));
			return new ResponseEntity<>(dailyInfo.get(),
					dailyInfo.isPresent() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
