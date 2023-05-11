package com.iotstar.onlinetest.services.wishList;

import com.iotstar.onlinetest.DTOs.requests.WishItemRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.DTOs.responses.WishItemResponse;
import com.iotstar.onlinetest.DTOs.responses.WishListResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.WishList;
import com.iotstar.onlinetest.models.WishItem;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.WishItemDAO;
import com.iotstar.onlinetest.repositories.WishListDAO;
import com.iotstar.onlinetest.services.topic.TopicServiceImp;
import com.iotstar.onlinetest.statval.EWish;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListServiceImp extends WishListPaging  implements WishListService {
    @Autowired
    private WishListDAO wishListDAO;
    @Autowired
    @Lazy
    private TopicServiceImp topicServiceImp;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private WishItemDAO wishItemDAO;

    public WishList getWishListReturnWishList(Long userId){
        return wishListDAO.findByUser_UserId(userId).orElseThrow(()->
                new ResourceNotFoundException(EWish.WISHLIST_NOT_FOUND.getDescription()));
    }

    public List<WishItem> getWishItems(Long wishListId){
        return wishItemDAO.findWishItemsByWishListId(wishListId, pageable()).orElseThrow(()->
                new ResourceNotFoundException(EWish.WISHLIST_NOT_FOUND.getDescription()));
    }

    public WishItem getWishItem(Long topicId, Long wishListId){
        return wishItemDAO.findByTopicIdInWishListId(topicId, wishListId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.NOT_FOUND));
    }

    public void addWishItem(WishItemRequest wishItemRequest){
        WishList wishList = getWishListReturnWishList(wishItemRequest.getUserId());
        Topic topic = topicServiceImp.getTopicReturnTopic(wishItemRequest.getTopicId());
        WishItem wishItem = new WishItem();
        wishItem.setTopic(topic);
        wishItem.setWishList(wishList);

        wishItemDAO.save(wishItem);
    }
    public void createWishList(User user){
        WishList wishList = new WishList();
        wishList.setUser(user);
        wishListDAO.save(wishList);
    }

    @Override
    public WishListResponse getWishListByUserId(Long userId) {
        WishList wishList = getWishListReturnWishList(userId);
        List<WishItem> wishItems = getWishItems(wishList.getWishListId());
        List<WishItemResponse> wishItemResponses = new ArrayList<>();
        for (WishItem i : wishItems){
            wishItemResponses.add(new WishItemResponse(mapper.map(i.getTopic(), TopicResponse.class)));
        }

        WishListResponse wishListResponse = new WishListResponse();
        wishListResponse.setWishItemRespons(wishItemResponses);
        return wishListResponse;
    }

    @Override
    public void delWishItem(WishItemRequest wishItemRequest) {
        WishList wishList = getWishListReturnWishList(wishItemRequest.getUserId());
        WishItem wishITem = getWishItem(wishItemRequest.getTopicId(), wishList.getWishListId());
        wishItemDAO.delete(wishITem);
    }
}
