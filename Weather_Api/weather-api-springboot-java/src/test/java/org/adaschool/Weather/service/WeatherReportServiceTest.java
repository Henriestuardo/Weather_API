package org.adaschool.Weather.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    void testGetWeatherReport() {
        // Datos de prueba
        WeatherApiResponse.Main mockMain = new WeatherApiResponse.Main();
        mockMain.setTemperature(25.5);
        mockMain.setHumidity(60.0);

        WeatherApiResponse mockResponse = new WeatherApiResponse();
        mockResponse.setMain(mockMain);

        // Configuración del mock
        when(restTemplate.getForObject(anyString(), eq(WeatherApiResponse.class))).thenReturn(mockResponse);

        // Llamada al método y validación
        WeatherReport report = weatherReportService.getWeatherReport(37.8267, -122.4233);

        assertNotNull(report);
        assertEquals(25.5, report.getTemperature());
        assertEquals(60.0, report.getHumidity());
    }
}
