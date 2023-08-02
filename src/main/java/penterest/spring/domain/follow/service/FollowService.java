package penterest.spring.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import penterest.spring.domain.follow.entity.Follow;
import penterest.spring.domain.follow.repository.FollowRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public Boolean addFollow(String toAccount, String fromAccount) throws Exception {
        if(Objects.equals(toAccount, fromAccount)) {
            throw new Exception();
        }

        Member toMember = memberRepository.findByEmail(toAccount);

        Member fromMember = memberRepository.findByEmail(fromAccount);

        Optional<Follow> relation = getFollowRelation(toMember.getEmail(), fromMember.getEmail());
        if(relation.isPresent()) {
            throw new Exception("Already exists");
        }
        followRepository.save(new Follow(toMember.getEmail(), fromMember.getEmail()));

        return true;
    }

    public Boolean unFollow(String toAccount, String fromAccount) throws Exception {
        if(Objects.equals(toAccount, fromAccount)) {
            throw new Exception();
        }
        Member toMember = memberRepository.findByEmail(toAccount);

        Member fromMember = memberRepository.findByEmail(fromAccount);

        Optional<Follow> relation = getFollowRelation(toMember.getEmail(), fromMember.getEmail());
        if(relation.isEmpty()) {
            throw new Exception("No exists");
        }
        followRepository.delete(relation.get());

        return true;
    }
    private Optional<Follow> getFollowRelation(String toAccount, String fromAccount) {
        return followRepository.findByToMemberAndFromMember(toAccount, fromAccount);
    }

//    public List<Member> findFollowingMembers(String email) {
//        Member member = memberRepository.findByEmail(email);
//        List<Follow> followingList = followRepository.findByFromMember(member.getId());
//        return followingList.stream().map(Follow::getToAccount).map(memberRepository::findById).toList();
//    }
//
//    public List<Member> findFollowerMembers(String email) {
//        Member member = memberRepository.findByEmail(email);
//        Follow followerList = followRepository.findByToMember(member.getId());
//        return followerList.stream().map(Follow::getFromAccount).map(memberRepository::findById).toList();
//    }

    public List<String> getFollowingList(String email) throws Exception {
        List<Member> followingList = (List<Member>) memberRepository.findAllByEmail(email);

        // Check if the list is not empty and return an appropriate response
        if (followingList.isEmpty()) {
            throw new Exception("없음");
        } else {
            List<String> emailList = followingList.stream().map(Member::getEmail).collect(Collectors.toList());
            return emailList;
        }
    }

    public List<String> getFollowerList(String email) throws Exception {
        List<Member> followerList = (List<Member>) memberRepository.findAllByEmail(email);

        // Check if the list is not empty and return an appropriate response
        if (followerList.isEmpty()) {
            throw new Exception("없음");
        } else {
            List<String> emailList = followerList.stream().map(Member::getEmail).collect(Collectors.toList());
            return emailList;
        }
    }

//    public Long getFollowingCount(String email) {
//        Member member = memberRepository.findByEmail(email);
//
//        return followRepository.countByToAccount(member.getId());
//    }
//
//    public Long getFollowerCount(String email) {
//        Member member = memberRepository.findByEmail(email);
//
//        return followRepository.countByFromAccount(member.getId());
//    }

//    public boolean ifFollowingExist(Long id){
//        if(followRepository.findAllByToMember(id) != null){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    public boolean ifFollowerExist(Long id) {
//        if(followRepository.findAllByFromMember(id) != null){
//            return true;
//        }else{
//            return false;
//        }
}

