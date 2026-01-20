package port.example.Portfolio.Service;

import org.springframework.stereotype.Service;
import port.example.Portfolio.Dto.ProfileDTO;
import port.example.Portfolio.Repository.ProfileRepository;
import port.example.Portfolio.Repository.SkillRepository;
import port.example.Portfolio.entity.Profile;
import port.example.Portfolio.entity.Skill;


@Service
public class ProfileService {

    private final ProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public ProfileService(ProfileRepository profileRepo, SkillRepository skillRepo) {
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
    }

    public ProfileDTO getProfile() {
        Profile profile = profileRepo.findById(1L)
                .orElse(new Profile());

        return mapToDTO(profile);
    }

    public ProfileDTO saveProfile(ProfileDTO dto) {

        Profile profile = profileRepo.findById(dto.id != null ? dto.id : 1L)
                .orElse(new Profile());

        profile.setName(dto.name);
        profile.setTitle(dto.title);
        profile.setBio(dto.bio);
        profile.setEmail(dto.email);
        profile.setPhone(dto.phone);
        profile.setLocation(dto.location);
        profile.setWebsite(dto.website);
        profile.setProfileImage(dto.profileImage);

        // CLEAR old skills
        profile.getSkills().clear();

        // ADD new skills
        for (String skillName : dto.skills) {
            Skill skill = skillRepo.findByName(skillName)
                    .orElseGet(() -> {
                        Skill s = new Skill();
                        s.setName(skillName);
                        return skillRepo.save(s);
                    });
            profile.getSkills().add(skill);
        }

        profileRepo.save(profile);
        return mapToDTO(profile);
    }

    private ProfileDTO mapToDTO(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.id = profile.getId();
        dto.name = profile.getName();
        dto.title = profile.getTitle();
        dto.bio = profile.getBio();
        dto.email = profile.getEmail();
        dto.phone = profile.getPhone();
        dto.location = profile.getLocation();
        dto.website = profile.getWebsite();
        dto.profileImage = profile.getProfileImage();

        dto.skills = profile.getSkills()
                .stream()
                .map(Skill::getName)
                .toList();

        return dto;
    }
}
