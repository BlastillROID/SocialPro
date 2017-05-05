<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Announcment
 *
 * @ORM\Table(name="announcment")
 * @ORM\Entity
 */
class Announcment
{
    /**
     * @var integer
     *
     * @ORM\Column(name="announce_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $announceId;

    /**
     * @var string
     *
     * @ORM\Column(name="title", type="string", length=50, nullable=false)
     */
    private $title;

    /**
     * @var string
     *
     * @ORM\Column(name="desc", type="string", length=250, nullable=false)
     */
    private $desc;

    /**
     * @var integer
     *
     * @ORM\Column(name="price", type="integer", nullable=false)
     */
    private $price;


}

