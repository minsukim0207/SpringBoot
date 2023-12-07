package com.kh.springdb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Item;
import com.kh.springdb.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	// 모든 상품 리스트
	public List<Item> allItemView() {
		return itemRepository.findAll();
	}
	
	// 상품 등록
	// 이미지 업로드를 위한 파라미터 추가 작성
	public void saveItem(Item item, MultipartFile imgFile) throws IllegalStateException, IOException {
		String originName = imgFile.getOriginalFilename();
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img";
		File saveFile = new File(originName, projectPath);
		imgFile.transferTo(saveFile);
		item.setImgName(originName);
		item.setImgPath("/img/" + projectPath);
		itemRepository.save(item);
	}

	// 상세보기, 수정하기 메서드
	public Item getItemById(int id) {
		return itemRepository.findItemById(id);
	}
}
