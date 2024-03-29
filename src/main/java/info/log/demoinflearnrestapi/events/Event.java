package info.log.demoinflearnrestapi.events;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
// @Data를 쓰지 않는 이유
// EqualsAndHashCode()는 default로 모든 fields를 사용하기 때문에 상호참조 현상이 발생하여 오버플로가 발생할 수 있음
public class Event {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 몽미
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    //Default는 EnumType.ORDINAL
    // 하지만 이후 Enum의 순서가 변경될 수 있으니 STRING으로 바꿔주자
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;

    public void update() {
        // Update free
        this.free = this.basePrice == 0 && this.maxPrice == 0;

        // Update offline
        this.offline = this.location != null && !(this.location.isBlank());
    }
}
