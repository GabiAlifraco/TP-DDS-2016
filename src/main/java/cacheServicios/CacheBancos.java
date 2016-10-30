package cacheServicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import DTOs.BancoDTO;
import json.JsonFactory;
import redis.clients.jedis.Jedis;

public class CacheBancos {

	Jedis db;
	JsonFactory jsonFactory = new JsonFactory();

	public CacheBancos(Jedis db) {
		this.db = db;
	}

	public void guardar(List<BancoDTO> bancosServicio, String banco, String servicio) {
		int unDia = 86400;
		String json = bancosServicio.stream().map(bancoDTO -> jsonFactory.toJson(bancoDTO) + ",").collect(Collectors.joining());

		String jsonRespuesta = "[" + json.substring(0, json.length() - 1) + "]";
		
		db.setex(banco + servicio, unDia, jsonRespuesta);
	}

	public List<BancoDTO> buscar(String banco, String servicio) {
		if(db.exists(banco + servicio)){
		return Arrays.asList(jsonFactory.fromJson(db.get(banco + servicio), BancoDTO[].class));
		} else {
			List<BancoDTO> listaVacia = new ArrayList<BancoDTO>();
			return listaVacia;
		}
	}

	public Jedis getDb() {
		return db;
	}

	public void setDb(Jedis db) {
		this.db = db;
	}
}
