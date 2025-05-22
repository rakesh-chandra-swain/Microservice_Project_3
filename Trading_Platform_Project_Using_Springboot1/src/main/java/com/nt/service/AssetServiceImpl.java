package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Asset;
import com.nt.model.Coin;
import com.nt.model.User;
import com.nt.respository.AssetRepository;

@Service
public class AssetServiceImpl implements AssetService {
	
	@Autowired
	private AssetRepository assetRepository;

	@Override
	public Asset createAseet(User user, Coin coin, double quantity) {
		Asset asset=new Asset();
		asset.setUser(user);
		asset.setCoin(coin);
		asset.setQuantity(quantity);
		asset.setBuyPrice(quantity);
		return assetRepository.save(asset);
	}

	@Override
	public Asset getAssetById(Long assetId) throws Exception {
	
		return assetRepository.findById(assetId).orElseThrow(()->new Exception("asset not found"));
	}

	@Override
	public Asset getAseetByUserIdAndId(Long userId, Long assetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Asset> getUsersAssets(Long userId) {
		
		return assetRepository.findByUserId(userId);
	}

	@Override
	public Asset updateAsset(Long assetId, double quantity) throws Exception {
		
		Asset oldAsset=getAssetById(assetId);
		oldAsset.setQuantity(quantity+oldAsset.getQuantity());
		return assetRepository.save(oldAsset);
	}

	@Override
	public Asset findAssetByUserIdAndCoinId(Long userId, String coinId) {
		
		return assetRepository.findByUserIdAndCoinId(userId, userId);
	}

	@Override
	public void deleteAsset(Long assetId) {
		
		assetRepository.deleteById(assetId);

	}

}
