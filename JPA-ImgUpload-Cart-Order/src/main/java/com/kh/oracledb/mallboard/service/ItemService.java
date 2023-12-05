package com.kh.oracledb.mallboard.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.kh.oracledb.mallboard.repository.ItemRepository;
import com.kh.oracledb.mallboard.vo.Item;

public class ItemService {
	
	private final ItemRepository itemRepository;
	//private final CartService cartService;
	//private final SaleService saleService;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	public void saveItem(Item item, MultipartFile photoFile) throws Exception {
		// 상품명 저장될 파일명 경로 생성
		// 이미지 파일 정보에 대해서 추출
		String originPhotoName = photoFile.getOriginalFilename();	// 업로드 된 이미지 파일에 원본 파일명을 가져옴
		String photoName = "";

		// 업로드된 이미지 파일의 원본 파일명을 가져옴
		String photoPath = System.getProperty("user.dir") + "src/main/resources/static/uploadImg/"; // System.getProperty("user.dir") <= 현재 코드가 작업하고 있는 폴더 위치 System.getProperty(), "user.dir" 사용자 폴더
		// user.dir = C:/Users/user1/springBoot-workspace/JPA-ImgUpload-Cart-Order

		UUID uuid = UUID.randomUUID();
		
		String saveFileName = "khShop_" + originPhotoName;
		photoName = saveFileName;	// 빈 값에 한번 더 재정의로 넣어줌
		File saveFile = new File(photoPath, photoName);
		photoFile.transferTo(saveFile); // 업로드 된 이미지 파일을 지정된 경로에 저장
		item.setPhotoName(photoName);
		item.setPhotoPath("/img/" + photoName);
		
		itemRepository.save(item);
	}

	public Item getItemById(Integer id) {
		return itemRepository.findById(id).get();
	}
	// findById를 사용해서 Id에 해당하는 값을 가져오고
	// get을 이용해서 Item 객체를 반환
	
	public List<Item> allItemList() {
		return itemRepository.findAll();
	}
	
	public void itemDelete(Integer Id) {
		itemRepository.deleteById(Id);
	}
}
