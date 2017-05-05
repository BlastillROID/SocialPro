<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Events
 *
 * @ORM\Table(name="events")
 * @ORM\Entity
 */
class Events
{
    /**
     * @var integer
     *
     * @ORM\Column(name="event_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $eventId;

    /**
     * @var integer
     *
     * @ORM\Column(name="type_id", type="integer", nullable=false)
     */
    private $typeId;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="event_date", type="date", nullable=false)
     */
    private $eventDate;

    /**
     * @var integer
     *
     * @ORM\Column(name="event_adress", type="integer", nullable=false)
     */
    private $eventAdress;

    /**
     * @var string
     *
     * @ORM\Column(name="event_description", type="text", length=65535, nullable=false)
     */
    private $eventDescription;

    /**
     * @var string
     *
     * @ORM\Column(name="event_media", type="blob", length=65535, nullable=false)
     */
    private $eventMedia;


}

